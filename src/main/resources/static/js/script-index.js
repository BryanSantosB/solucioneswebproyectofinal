//Select1
const check1 = document.getElementById("check-1");
const i_1_1 = document.getElementById("i-1-1");
const i_2_1 = document.getElementById("i-2-1");
const caja_opciones1 = document.getElementById("caja1");

//Select2
const check2 = document.getElementById("check-2");
const i_1_2 = document.getElementById("i-1-2");
const i_2_2 = document.getElementById("i-2-2");
const caja_opciones2 = document.getElementById("caja2");

//Menú responsive 
const check_res = document.getElementById("check-res");
const menu = document.getElementById("contenedor-en");
const overlay = document.getElementById("oscuro");

//Select-Footer
const check_ft1 = document.getElementById("ft-1");
const check_ft2 = document.getElementById("ft-2");
const ul_ft1 = document.getElementById("ul-ft-1");
const ul_ft2 = document.getElementById("ul-ft-2");

//Cambiar ícono del elemento select y mostrar sus opciones
function cambiarIconoMostrarOpciones(check, i1, i2, caja){
    if(check.checked){
        i1.style.display = "none";
        i2.style.display = "inline-block";
        caja.style.display = "block";
    }else{
        i1.style.display = "inline-block";
        i2.style.display = "none";
        caja.style.display = "none";
    }
}

function comprobarCheck() {
    if (check_res.checked) {
        menu.style.left = "45%";
        menu.style.boxShadow = "-5px 16px 20px rgba(0,0,0,0.25)";
    } else {
        menu.style.left = "-100%";
        menu.style.boxShadow = "none";
    }
}

//Función para oscurecer el fondo
function oscuro() {
    if (check_res.checked) {
        overlay.style.display = "block";
    } else {
        overlay.style.display = "none";
    }
}

function comprobarCheckFooter(check, ul_ft){
    if(check.checked===false && window.innerWidth <= 767){
        ul_ft.style.display = "none";
    }else{
        ul_ft.style.display = "block";
    }
}

//Eventos select
check1.addEventListener("change", function(){
    cambiarIconoMostrarOpciones(check1, i_1_1,i_2_1 ,caja_opciones1);
})

check2.addEventListener("change", function(){
    cambiarIconoMostrarOpciones(check2, i_1_2,i_2_2,caja_opciones2);
})

//Evento del check responsive
check_res.addEventListener("change", function(){
    comprobarCheck();
    oscuro();
});

//Evento del check footer
check_ft1.addEventListener("change", function(){
    comprobarCheckFooter(check_ft1, ul_ft1);
});

check_ft2.addEventListener("change", function(){
    comprobarCheckFooter(check_ft2, ul_ft2);
});

//Evento al recargar la página o redimensionar
window.addEventListener('load', function() { //Evento al cargar página
        
    function desmarcarCheckbox() {
        if (window.innerWidth > 767) {
            check_res.checked = false;
            comprobarCheck();
            comprobarCheckFooter(check_ft1, ul_ft1);
            comprobarCheckFooter(check_ft2, ul_ft2);
            oscuro();
        }
    }
    this.addEventListener('resize', desmarcarCheckbox); //Evento al redimensionar página
    desmarcarCheckbox();
});

function redirectRealizarPedido(){
    window.location.href = `/realizarpedido/`;
}