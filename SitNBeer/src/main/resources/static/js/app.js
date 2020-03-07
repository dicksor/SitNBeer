document.addEventListener('DOMContentLoaded', function () {
    var ranges  = document.querySelectorAll("input[type=range]");
    M.Range.init(ranges);

    var elems = document.querySelectorAll('.dropdown-trigger')
    let dropdownOptions = {
        'hover': true,
        'constrainWidth': false
    }
    var instances = M.Dropdown.init(elems, dropdownOptions)


    var sizeBarSlide = document.getElementById('pageSizeBars')
    sizeBarSlide.onchange = function() {
        const url = window.location.origin + window.location.pathname + "?size=" + this.value
        window.location.assign(url)
    }

    var sizeBeerSlide = document.getElementById('pageSizeBeers')
    sizeBeerSlide.onchange = function() {
        const url = window.location.origin + window.location.pathname + "?size=" + this.value
        window.location.assign(url)
    }
})