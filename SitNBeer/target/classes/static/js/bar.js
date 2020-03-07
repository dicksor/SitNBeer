document.addEventListener('DOMContentLoaded', function () {
    var sizeBarSlide = document.getElementById('pageSizeBars')
    sizeBarSlide.onchange = function() {
        const url = window.location.origin + window.location.pathname + "?size=" + this.value
        window.location.assign(url)
    }
})