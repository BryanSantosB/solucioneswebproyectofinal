let carritoItems = [];
let total = 0;
let cantidadGlobal = 0;
const totalTabla = document.getElementById("totalTabla");
totalTabla.textContent = total;

async function agregarAlCarrito() {
    const selectProducto = document.getElementById("select-producto");
    const cantidadInput = document.getElementById("cantidad-productos");

    const selectedProductoValue = selectProducto.value;
    const selectedProductoText = selectProducto.options[selectProducto.selectedIndex].text;
    const cantidad = cantidadInput.value;

    // Obtener el precio del producto seleccionado
    const selectedProducto = selectProducto.options[selectProducto.selectedIndex];
    const precio = selectedProducto.getAttribute("data-precio");

    const itemCarrito = {
        IdProducto: parseInt(selectedProductoValue),
        Nombre: selectedProductoText,
        Precio: parseFloat(precio),
        Cantidad: parseInt(cantidad)
    };

    
    console.log(itemCarrito)
    if (Object.values(itemCarrito).some((value) => !value)) {
        alert("Por favor, complete todos los campos obligatorios.");
        return;
    }



    //Agregar el elemento al carrito local en JavaScript
    if ((cantidadGlobal + itemCarrito.Cantidad) <= 8) {

        //Crear una nueva fila en la tabla y agregar celdas con los valores
        const tablaCarrito = document.getElementById("tabla-carrito").getElementsByTagName('tbody')[0];
        const nuevaFila = tablaCarrito.insertRow();
        const celdaIdProducto = nuevaFila.insertCell(0);
        const celdaNombre = nuevaFila.insertCell(1);
        const celdaPrecio = nuevaFila.insertCell(2);
        const celdaCantidad = nuevaFila.insertCell(3);
        const celdaSubtotal = nuevaFila.insertCell(4);

        cantidadGlobal += itemCarrito.Cantidad;
        carritoItems.push(itemCarrito);
        celdaIdProducto.textContent = selectedProductoValue;
        celdaNombre.textContent = selectedProductoText;
        celdaPrecio.textContent = precio;
        celdaCantidad.textContent = cantidad;

        const subtotal = precio * cantidad; 
        celdaSubtotal.textContent = subtotal;

        total = total + subtotal;
    } else {
        Swal.fire(
            `Productos excedidos`,
            `El máximo de productos por pedidos es 8, usted lleva ${cantidadGlobal}`,
            'error'
        );
    }

    totalTabla.textContent = parseFloat(total);

    try {
        const res = await fetch(`/realizarpedido/agregarCarrito?id=${itemCarrito.IdProducto}&nombre=${itemCarrito.Nombre}&precio=${itemCarrito.Precio}&cantidad=${itemCarrito.Cantidad}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        })

    } catch (error) {
        console.error(error);
    }
}

//Agregamos al carrito los productos
const btnAgregarCarrito = document.getElementById("btn-agregar-carrito");
btnAgregarCarrito.addEventListener("click", agregarAlCarrito);

//Evento de cambio en categoría, se lista producto dependiendo la 
//categoría seleccionada
$(document).ready(function() {
    const categoriaSelect = $('#select-categoria');
    const productoSelect = $('#select-producto');

    categoriaSelect.change(function() {
        const categoriaSeleccionada = categoriaSelect.val();
        productoSelect.empty().append('<option value="">Seleccione un producto</option>');
        if (categoriaSeleccionada) {
            $.ajax({
                type: 'GET',
                url: `/productos/obtenerproductoporcategoria/${categoriaSeleccionada}`,
                dataType: 'json',
                success: function(productos) {
                    productos.forEach(producto => {
                        productoSelect.append(`<option value="${producto.id}" data-precio="${producto.precio}">${producto.nombre}</option>`);
                    });
                },
                error: function(error) {
                    console.error('Error al obtener productos por categoría', error);
                }
            });
        }
    });
});

//Mostrar formulario de pago con tarjeta
const selectPago = document.getElementById('select-mpago');
const formulario_tarjeta = document.getElementById('pagoOnline');

selectPago.addEventListener('change', () => {
    const indice = selectPago.selectedIndex;
    const valor = selectPago.options[indice].value;
    if (valor == 1) {
        formulario_tarjeta.style.display = 'block';
    } else {
        formulario_tarjeta.style.display = 'none';
    }
});

//Validar que  existan productos en tabla antes de mostrar el método de pago
const btnRealizarPago = document.getElementById("btnRealizarPedido");
const modal = new bootstrap.Modal(document.getElementById("exampleModal"));

btnRealizarPago.addEventListener("click", function () {
    if (carritoItems.length > 0) {
        modal.show();
    } else {
        Swal.fire(
            `Carrito Vacío`,
            `Debe seleccionar al menos 1 producto`,
            'error'
        );
    }
});

//Mandar lista y método de pago
const btnRealizarPedido = document.getElementById("btn-realizar-pedido");
const btnRealizarPedidoT = document.getElementById("btn-realizar-pedido-tarjeta");
const btnRealiarPedidoIndefinido = document.getElementById('btn-realizar-pedido-indefinido');
const selectMetodoP = document.getElementById("select-mpago");
const input_direccion = document.getElementById("direccion");
selectMetodoP.addEventListener('change', () => {
    if (selectMetodoP.value == 2 || selectMetodoP.value == 0) {
        btnRealizarPedido.style.display = "block";
        btnRealizarPedidoT.style.display = "none";
        btnRealiarPedidoIndefinido.style.display = 'none';
    } else if (selectMetodoP.value == 1) {
        btnRealizarPedido.style.display = "none";
        btnRealizarPedidoT.style.display = "block";
        btnRealiarPedidoIndefinido.style.display = 'none';
    } else {
        btnRealizarPedido.style.display = "none";
        btnRealizarPedidoT.style.display = "none";
        btnRealiarPedidoIndefinido.style.display = 'block';
    }
});
btnRealizarPedido.addEventListener("click", realizarPedidoT);
btnRealizarPedidoT.addEventListener("click", realizarPedidoT);
btnRealiarPedidoIndefinido.addEventListener('click', realizarPedidoT);

//Próximamente a utilizar
async function agregarcarritoDB() {
    
    const pedidoCompleto = {
        metodopago: metodopagovalue,
        direccion: direccionvalue,
        carrito: carritoItems
    }

    console.log(pedidoCompleto)
    console.log(carritoItems)

    try {
        const res = await fetch('/realizarpedido/completarpedido', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pedidoCompleto),
        })

    } catch (error) {
        console.error(error);
    }
}


async function realizarPedidoT() {
    
    let metodopagovalue = selectPago.value;
    let direccionvalue = input_direccion.value;

    const pedidoCompleto = {
        metodopago: metodopagovalue,
        direccion: direccionvalue,
        carrito: carritoItems
    }

    console.log(pedidoCompleto)
    console.log(carritoItems)

    try {
        const res = await fetch(`/realizarpedido/completarpedido?metodopago=${metodopagovalue}&direccion=${direccionvalue}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pedidoCompleto),
        }).then(responseText => {
            modal.hide();
            Swal.fire(
                'Pedido Realizado',
                `Total del pedido: S/${total}`,
                'success'
            ).then(() => {
                window.location.href = `/inicio/`;
            });
        })

    } catch (error) {
        console.error(error);
    }
}



