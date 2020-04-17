/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

function acceptOrder(element) {
  let orderId = element.parentNode.parentNode.id;

  fetch("/order/update/IN_PROCESS/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if (data.status == "OK") {
        document.querySelector("#" + orderId + " .order-status").textContent = data.orderStatus;
        document.querySelector("#" + orderId + " .accept-button").outerHTML = '<a class="waves-effect waves-light btn close-button" onclick="closeOrder(this)">Close</a>'
        document.querySelector('#' + orderId + " .reject-button").remove()
      }
    })
}

function closeOrder(element) {
  let orderId = element.parentNode.parentNode.id;

  fetch("/order/update/CLOSE/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if (data.status == "OK") {
        document.querySelector("#" + orderId).remove()
      }
    })
}

function rejectOrder(element) {
  let orderId = element.parentNode.parentNode.id;

  fetch("/order/update/REJECTED/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if (data.status == "OK") {
        document.querySelector("#" + orderId).remove()
      }
    })
}

function deleteOrder(element) {
  let orderId = element.parentNode.parentNode.id;

  fetch("/order/delete/" + orderId.split("-")[1])
    .then(res => res.json())
    .then((data) => {
      if (data.status == "OK") {
        document.querySelector("#" + orderId).remove()
      }
    })
}