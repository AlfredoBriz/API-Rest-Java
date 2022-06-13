window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

//----------------------------------------------------------------------------------------------------------------------


async function registrarUsuario(){

    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;

    datos.telefono = document.getElementById('txtTelefono').value;
    if (datos.telefono <= 9999999){
        alert('El número ingresado no corresponde a un Teléfono.');
        return;
    }

    datos.password = document.getElementById('txtPassword').value;

    let repetirPassword = document.getElementById('txtConfirmarPassword').value;

    if (repetirPassword != datos.password){
        alert('La contraseña que ingresaste es diferente.');
        return;
    }



    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      alert("La cuenta fue creada con exito!");
      window.location.href = 'login.html'

    }

//----------------------------------------------------------------------------------------------------------------------















