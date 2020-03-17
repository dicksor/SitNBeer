document.addEventListener('DOMContentLoaded', function () {
    var sizeBeerSlide = document.getElementById('pageSizeBeers')
    sizeBeerSlide.onchange = function () {
        let url = new URL(window.location)
        let params = new URLSearchParams(url.search)
        params.set('size', this.value)
        url.searchParams.set('size', params.get('size'))
        window.location.assign(url)
    }

    var beerPrice = document.getElementById('beerPrice')
    beerPrice.onchange = function(){
        let url = new URL(window.location)
        url.pathname = 'beer/query'
        let params = new URLSearchParams(url.search)
        let searchQuery = `(price<${this.value})`
        params.set('search', searchQuery)
        url.searchParams.set('search', params.get('search'))
        window.location.assign(url)
    }

    var btnBeerSearchTool = document.getElementById('btnBeerSearchTool')
    btnBeerSearchTool.onclick = function () {
        let toolBar = document.getElementById('inBeerSearch')
        toolBar.style.height = toolBar.style.height === '0px' ? '200px' : '0px'
        toolBar.style.opacity = toolBar.style.opacity === '0' ? '1' : '0'
    }
})