/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

document.addEventListener('DOMContentLoaded', function () {
    var sizeBarSlide = document.getElementById('pageSizeBars')
    sizeBarSlide.onchange = function () {
        let url = new URL(window.location)
        let params = new URLSearchParams(url.search)
        params.set('size', this.value)
        url.searchParams.set('size', params.get('size'))
        window.location.assign(url)
    }

    var numTables = document.getElementById('numTables')
    numTables.onchange = function () {
        let url = new URL(window.location)
        url.pathname = 'bar/query'
        let params = new URLSearchParams(url.search)
        let searchQuery = `(availableTable>${this.value})`
        params.set('search', searchQuery)
        url.searchParams.set('search', params.get('search'))
        window.location.assign(url)
    }

    var btnBarSearchTool = document.getElementById('btnBarSearchTool')
    btnBarSearchTool.onclick = function () {
        let toolBar = document.getElementById('inBarSearch')
        toolBar.style.height = toolBar.style.height === '0px' ? '200px' : '0px'
        toolBar.style.opacity = toolBar.style.opacity === '0' ? '1' : '0'
    }
})