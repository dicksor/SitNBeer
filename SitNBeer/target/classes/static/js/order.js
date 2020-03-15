function acceptOrder(element){
    let orderId = element.parentNode.parentNode.id;

    fetch("/order/accept/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if(data.status == "OK"){
        document.querySelector("#" + orderId + " .order-status").textContent = data.orderStatus;
        document.querySelector("#" + orderId + " .accept-button").outerHTML = '<a class="waves-effect waves-light btn close-button" onclick="closeOrder(this)">Close</a>'
      }   
    })
}

function closeOrder(element){
    let orderId = element.parentNode.parentNode.id; 

    fetch("/order/close/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if(data.status == "OK"){
        document.querySelector("#" + orderId).remove()
      }   
    })
}

function rejectOrder(element){
    let orderId = element.parentNode.parentNode.id; 

    fetch("/order/reject/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if(data.status == "OK"){
        document.querySelector("#" + orderId).remove()
      }   
    })
}