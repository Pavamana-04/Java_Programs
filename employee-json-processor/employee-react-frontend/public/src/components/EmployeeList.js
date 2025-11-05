import React, { useState, useEffect } from 'react';
import EmployeeCard from './EmployeeCard';
import employeeService from '../services/employeeService';
import './EmployeeList.css';

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  // Load employees on component mount
  useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = async () => {
    try {
      setLoading(true);
      const data = await employeeService.getAllEmployees();
      setEmployees(data);
      setError('');
    } catch (err) {
      setError('Failed to load employees. Make sure backend is running.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleLoadFromJSON = async () => {
    try {
      setLoading(true);
      await employeeService.loadFromJSON();
      await loadEmployees(); // Reload the list
      alert('Employees loaded successfully from JSON!');
    } catch (err) {
      alert('Error loading from JSON: ' + err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this employee?')) {
      try {
        await employeeService.deleteEmployee(id);
        await loadEmployees(); // Reload the list
        alert('Employee deleted successfully!');
      } catch (err) {
        alert('Error deleting employee: ' + err.message);
      }
    }
  };

  const handleEdit = (employee) => {
    // For demo - just show an alert
    alert(`Edit employee: ${employee.name}\nThis would open an edit form.`);
  };

  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
        <p>Loading employees...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="error">
        <h3>❌ Error</h3>
        <p>{error}</p>
        <button onClick={loadEmployees} className="btn-retry">
          🔄 Retry
        </button>
      </div>
    );
  }

  return (
    <div className="employee-list">
      <div className="header">
        <h1>👥 Employee Management System</h1>
        <div className="controls">
          <button onClick={handleLoadFromJSON} className="btn-load">
            📥 Load from JSON
          </button>
          <button onClick={loadEmployees} className="btn-refresh">
            🔄 Refresh
          </button>
          <span className="count">Total: {employees.length} employees</span>
        </div>
      </div>

      {employees.length === 0 ? (
        <div className="empty-state">
          <h3>No employees found</h3>
          <p>Click "Load from JSON" to load sample data</p>
        </div>
      ) : (
        <div className="employees-grid">
          {employees.map(employee => (
            <EmployeeCard
              key={employee.id}
              employee={employee}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default EmployeeList;