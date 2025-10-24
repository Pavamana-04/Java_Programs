// Mock backend using localStorage
const SERVER_URL = "http://localhost:8080";

// Mock API functions
const mockAPI = {
    login: (email, password) => {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                const users = JSON.parse(localStorage.getItem('users') || '[]');
                const user = users.find(u => u.email === email && u.password === password);

                if (user) {
                    resolve({ token: `mock-token-${Date.now()}`, user: { email: user.email } });
                } else {
                    reject(new Error("Invalid email or password"));
                }
            }, 500);
        });
    },

    register: (email, password) => {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                const users = JSON.parse(localStorage.getItem('users') || '[]');

                if (users.find(u => u.email === email)) {
                    reject(new Error("User already exists"));
                    return;
                }

                users.push({ email, password });
                localStorage.setItem('users', JSON.stringify(users));

                resolve({ token: `mock-token-${Date.now()}`, user: { email } });
            }, 500);
        });
    },

    getTodos: (token) => {
        return new Promise((resolve) => {
            setTimeout(() => {
                const todos = JSON.parse(localStorage.getItem(`todos-${token}`) || '[]');
                resolve(todos);
            }, 300);
        });
    },

    addTodo: (token, text) => {
        return new Promise((resolve) => {
            setTimeout(() => {
                const todos = JSON.parse(localStorage.getItem(`todos-${token}`) || '[]');
                const newTodo = {
                    id: `todo-${Date.now()}`,
                    text,
                    completed: false
                };
                todos.push(newTodo);
                localStorage.setItem(`todos-${token}`, JSON.stringify(todos));
                resolve(newTodo);
            }, 300);
        });
    },

    updateTodo: (token, id, completed) => {
        return new Promise((resolve) => {
            setTimeout(() => {
                const todos = JSON.parse(localStorage.getItem(`todos-${token}`) || '[]');
                const todo = todos.find(t => t.id === id);
                if (todo) {
                    todo.completed = completed;
                    localStorage.setItem(`todos-${token}`, JSON.stringify(todos));
                }
                resolve(todo);
            }, 300);
        });
    },

    deleteTodo: (token, id) => {
        return new Promise((resolve) => {
            setTimeout(() => {
                const todos = JSON.parse(localStorage.getItem(`todos-${token}`) || '[]');
                const filteredTodos = todos.filter(t => t.id !== id);
                localStorage.setItem(`todos-${token}`, JSON.stringify(filteredTodos));
                resolve({ success: true });
            }, 300);
        });
    }
};

// Login page logic
function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (!email || !password) {
        alert("Please fill in all fields");
        return;
    }

    mockAPI.login(email, password)
        .then(data => {
            localStorage.setItem("token", data.token);
            localStorage.setItem("userEmail", email);
            window.location.href = "todos.html";
        })
        .catch(error => {
            alert(error.message);
        });
}

// Register page logic
function register() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (!email || !password) {
        alert("Please fill in all fields");
        return;
    }

    mockAPI.register(email, password)
        .then(data => {
            localStorage.setItem("token", data.token);
            localStorage.setItem("userEmail", email);
            window.location.href = "todos.html";
        })
        .catch(error => {
            alert(error.message);
        });
}

// Todos page logic
function createTodoCard(todo) {
    const card = document.createElement("div");
    card.className = "todo-card";
    card.innerHTML = `
        <input type="checkbox" class="todo-checkbox" ${todo.completed ? 'checked' : ''}
               onchange="updateTodoStatus('${todo.id}', this.checked)">
        <span style="${todo.completed ? 'text-decoration: line-through; color: #888;' : ''}">
            ${todo.text}
        </span>
        <button onclick="deleteTodo('${todo.id}')">Delete</button>
    `;
    return card;
}

function loadTodos() {
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "login.html";
        return;
    }

    mockAPI.getTodos(token)
        .then(todos => {
            const todoList = document.getElementById("todo-list");
            const emptyMessage = document.getElementById("empty-message");

            if (todos.length === 0) {
                emptyMessage.textContent = "No todos yet. Add one above!";
                emptyMessage.style.display = "block";
                return;
            }

            emptyMessage.style.display = "none";
            todoList.innerHTML = "";

            todos.forEach(todo => {
                todoList.appendChild(createTodoCard(todo));
            });
        })
        .catch(error => {
            console.error("Error loading todos:", error);
            document.getElementById("empty-message").textContent = "Error loading todos";
        });
}

function addTodo() {
    const token = localStorage.getItem("token");
    const newTodoInput = document.getElementById("new-todo");
    const text = newTodoInput.value.trim();

    if (!text) {
        alert("Please enter a todo");
        return;
    }

    mockAPI.addTodo(token, text)
        .then(() => {
            newTodoInput.value = "";
            loadTodos();
        })
        .catch(error => {
            alert(error.message);
        });
}

function updateTodoStatus(id, completed) {
    const token = localStorage.getItem("token");

    mockAPI.updateTodo(token, id, completed)
        .then(() => {
            loadTodos();
        })
        .catch(error => {
            console.error("Error updating todo:", error);
            alert("Failed to update todo status");
        });
}

function deleteTodo(id) {
    const token = localStorage.getItem("token");

    mockAPI.deleteTodo(token, id)
        .then(() => {
            loadTodos();
        })
        .catch(error => {
            alert(error.message);
        });
}

// Page-specific initializations
document.addEventListener("DOMContentLoaded", function () {
    // Check if we're on todos page
    if (document.getElementById("todo-list")) {
        const token = localStorage.getItem("token");
        if (!token) {
            window.location.href = "login.html";
            return;
        }

        // Update heading with user email
        const userEmail = localStorage.getItem("userEmail");
        if (userEmail) {
            document.querySelector('h2').textContent = `${userEmail}'s Todos`;
        }

        loadTodos();

        // Add Enter key support for adding todos
        document.getElementById("new-todo").addEventListener("keypress", function(e) {
            if (e.key === "Enter") {
                addTodo();
            }
        });
    }

    // Check if we're on login/register pages and user is already authenticated
    if (document.getElementById("email") && !document.getElementById("todo-list")) {
        const token = localStorage.getItem("token");
        if (token) {
            window.location.href = "todos.html";
        }

        // Add Enter key support for forms
        const passwordField = document.getElementById("password");
        if (passwordField) {
            document.getElementById("email").addEventListener("keypress", function(e) {
                if (e.key === "Enter") {
                    passwordField.focus();
                }
            });

            passwordField.addEventListener("keypress", function(e) {
                if (e.key === "Enter") {
                    if (window.location.href.includes("login.html")) {
                        login();
                    } else if (window.location.href.includes("register.html")) {
                        register();
                    }
                }
            });
        }
    }
});