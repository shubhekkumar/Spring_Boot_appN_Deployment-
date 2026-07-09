const API = "http://localhost:8081/tasks";

window.onload = function () {
    loadTasks();
};

async function loadTasks() {

    const response = await fetch(API);

    const tasks = await response.json();

    const table = document.getElementById("taskTable");

    table.innerHTML = "";

    tasks.forEach(task => {

        table.innerHTML += `
            <tr>

                <td>${task.id}</td>

                <td>${task.title}</td>

                <td>${task.description}</td>

                <td>${task.status}</td>

                <td>

                    <button
                        class="btn btn-warning btn-sm me-2"
                        onclick="editTask(${task.id})">

                        Edit

                    </button>

                    <button
                        class="btn btn-danger btn-sm"
                        onclick="deleteTask(${task.id})">

                        Delete

                    </button>

                </td>

            </tr>
        `;
    });
}

async function createTask() {

    const task = {

        title: document.getElementById("title").value,

        description: document.getElementById("description").value,

        status: document.getElementById("status").value
    };

    const response = await fetch(API, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(task)

    });

    if (response.ok) {

        document.getElementById("title").value = "";

        document.getElementById("description").value = "";

        loadTasks();

    } else {

        alert("Validation Failed");

    }

}

async function deleteTask(id) {

    await fetch(API + "/" + id, {

        method: "DELETE"

    });

    loadTasks();

}

async function editTask(id) {

    const response = await fetch(API + "/" + id);

    const task = await response.json();

    document.getElementById("title").value = task.title;

    document.getElementById("description").value = task.description;

    document.getElementById("status").value = task.status;

    deleteTask(id);

}
