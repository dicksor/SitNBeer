document.addEventListener('DOMContentLoaded', function () {
    var sizeBarSlide = document.getElementById('pageSizeBars')
    sizeBarSlide.onchange = function() {
        let url = new URL(window.location)
        let params = new URLSearchParams(url.search)
        params.set('size', this.value)
        url.searchParams.set('size', params.get('size'))
        window.location.assign(url)
    }
})