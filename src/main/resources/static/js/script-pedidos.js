const selector_producto = document.getElementById('select-producto');
const imagen_producto = document.getElementById('imagen_producto');

selector_producto.addEventListener('change', () => {
    var indice_selector = selector_producto.selectedIndex;

    console.log(indice_selector);

    var valor = selector_producto.options[indice_selector].value;
    var texto = selector_producto.options[indice_selector].text;

    if(valor != ""){
        imagen_producto.src = `/public/img/${indice_selector}.jpg`;
    }
    console.log(valor, " ", texto)
    

});