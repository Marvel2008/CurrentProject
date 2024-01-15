document.addEventListener('DOMContentLoaded', function () {
    // Get the tooltip element
    var tooltip = document.getElementById('tooltip');

    // Get the element with the class "callback"
    var callbackElement = document.querySelector('.callback');

    // Add event listener for mouseenter
    callbackElement.addEventListener('mouseenter', function () {
        // Calculate the position of the tooltip relative to the callback element
        var rect = callbackElement.getBoundingClientRect();
        var topPosition = rect.bottom + window.scrollY;
        var leftPosition = rect.left + window.scrollX;

        // Set the position of the tooltip
        tooltip.style.top = topPosition + 'px';
        tooltip.style.left = leftPosition-28 + 'px';

        // Display the tooltip
        tooltip.style.display = 'block';
    });

    // Add event listener for mouseleave
    callbackElement.addEventListener('mouseleave', function () {
        // Hide the tooltip
        tooltip.style.display = 'none';
    });
});
// Configure CodeMirror
var editor = CodeMirror.fromTextArea(document.getElementById('editor'), {
    mode: "text/x-java",
    theme: "darcula",
    lineNumbers: true,
    autofocus: true
});

// Function to compile code
function compileCode() {
    // Get the code content
    var code = editor.getValue();

    // Simulate compilation (replace this with your actual compilation logic)
    var compilationResult = "Compilation successful!"; // Replace this with the actual result

    // Display the compilation result in the result textarea
    document.getElementById('result').value = compilationResult;
}

// Function to submit code (you can modify this function as needed)
function submitCode() {
    // Get the code content
    var code = editor.getValue();

    // Submit the code to the server using AJAX or any other method
    // You can handle code submission logic here
    // Example: using fetch API for simplicity
    fetch('/submit-code', {
        method: 'POST',
        body: JSON.stringify({ javaCode: code }),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // Handle the response from the server as needed
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
