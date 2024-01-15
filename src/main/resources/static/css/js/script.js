const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');
registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});
loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});
function togglePassword() {
    var passwordField = document.getElementById('password1');
    var eyeIcon = document.getElementById('eye-icon');

    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        eyeIcon.src = '/css/images/visibility_FILL0_wght400_GRAD0_opsz24.png'; // Шлях до іконки з відкритим око
    } else {
        passwordField.type = 'password';
        eyeIcon.src = '/css/images/visibility_off_FILL0_wght400_GRAD0_opsz24.png'; // Шлях до іконки з закритим оком
    }
}

function togglePassword2() {
    var passwordField = document.getElementById('password2');
    var eyeIcon = document.getElementById('eye-icon2');

    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        eyeIcon.src = '/css/images/visibility_FILL0_wght400_GRAD0_opsz24.png'; // Шлях до іконки з відкритим око
    } else {
        passwordField.type = 'password';
        eyeIcon.src = '/css/images/visibility_off_FILL0_wght400_GRAD0_opsz24.png'; // Шлях до іконки з закритим оком
    }
}

