document.addEventListener('DOMContentLoaded', function () {
    var sizeBarSlide = document.getElementById('pageSizeBars')
    sizeBarSlide.onchange = function() {
        let url = new URL(window.location)
        let params = new URLSearchParams(url.search)
        params.set('size', this.value)
        url.searchParams.set('size', params.get('size'))
        window.location.assign(url)
    }

    var numTables = document.getElementById('numTables')
    numTables.onchange = function(){
        let url = new URL(window.location)
        url.pathname = 'bar/query'
        let params = new URLSearchParams(url.search)
        let searchQuery = `(availableTable>${this.value})`
        params.set('search', searchQuery)
        url.searchParams.set('search', params.get('search'))
        window.location.assign(url)
    }
})