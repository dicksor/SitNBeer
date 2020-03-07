document.addEventListener('DOMContentLoaded', function () {
    var sizeBeerSlide = document.getElementById('pageSizeBeers')
    sizeBeerSlide.onchange = function() {
        const url = window.location.origin + window.location.pathname + "?size=" + this.value
        window.location.assign(url)
    }
})