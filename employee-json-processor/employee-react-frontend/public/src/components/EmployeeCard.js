import React from 'react';
import './EmployeeCard.css';

const EmployeeCard = ({ employee, onEdit, onDelete }) => {
  return (
    <div className="employee-card">
      <div className="employee-header">
        <h3>{employee.name}</h3>
        <span className="employee-id">ID: {employee.id}</span>
      </div>

      <div className="employee-details">
        <p><strong>📧 Email:</strong> {employee.email}</p>
        <p><strong>💼 Position:</strong> {employee.position}</p>
        <p><strong>🏢 Department:</strong> {employee.department}</p>
        <p><strong>💰 Salary:</strong> ${employee.salary.toLocaleString()}</p>
      </div>

      <div className="employee-actions">
        <button
          className="btn-edit"
          onClick={() => onEdit(employee)}
        >
          ✏️ Edit
        </button>
        <button
          className="btn-delete"
          onClick={() => onDelete(employee.id)}
        >
          🗑️ Delete
        </button>
      </div>
    </div>
  );
};

export default EmployeeCard;