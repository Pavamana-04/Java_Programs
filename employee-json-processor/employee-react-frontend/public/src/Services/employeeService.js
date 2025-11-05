import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/employees';

const employeeService = {
  // Get all employees
  getAllEmployees: async () => {
    try {
      const response = await axios.get(API_BASE_URL);
      return response.data;
    } catch (error) {
      console.error('Error fetching employees:', error);
      throw error;
    }
  },

  // Load employees from JSON
  loadFromJSON: async () => {
    try {
      const response = await axios.post(`${API_BASE_URL}/load-from-json`);
      return response.data;
    } catch (error) {
      console.error('Error loading from JSON:', error);
      throw error;
    }
  },

  // Create new employee
  createEmployee: async (employeeData) => {
    try {
      const response = await axios.post(API_BASE_URL, employeeData);
      return response.data;
    } catch (error) {
      console.error('Error creating employee:', error);
      throw error;
    }
  },

  // Update employee
  updateEmployee: async (id, employeeData) => {
    try {
      const response = await axios.put(`${API_BASE_URL}/${id}`, employeeData);
      return response.data;
    } catch (error) {
      console.error('Error updating employee:', error);
      throw error;
    }
  },

  // Delete employee
  deleteEmployee: async (id) => {
    try {
      await axios.delete(`${API_BASE_URL}/${id}`);
    } catch (error) {
      console.error('Error deleting employee:', error);
      throw error;
    }
  },

  // Get employee count
  getEmployeeCount: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/count`);
      return response.data;
    } catch (error) {
      console.error('Error getting count:', error);
      throw error;
    }
  }
};

export default employeeService;