// /*Desactivar todas las opciones del menú*/
// var elemento = document.getElementsByClassName("opMenu");
// for(var i=0; i<elemento.length;i++){
//     elemento[i].className = "nav-link opMenu";
// }

// var pro = document.getElementById("mProducto");
// pro.className = "nav-link opMenu activado";

(function(){
    var botonesEditar = document.querySelectorAll(".botonEditar")
    botonesEditar.forEach(item => {
        item.addEventListener("click", e => {
            document.getElementById('id').value = item.dataset.id;
            document.getElementById('txtNombre').value = item.dataset.nombre;
            document.getElementById('txtPrecio').value = item.dataset.precio;
            document.getElementById('selectCat').value = item.dataset.categoria;
            new bootstrap.Modal(document.getElementById('editarModal')).show();
        })
    })
})();