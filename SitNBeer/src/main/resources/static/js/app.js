document.addEventListener('DOMContentLoaded', function () {
    var ranges = document.querySelectorAll("input[type=range]");
    M.Range.init(ranges);

    var dropdowns = document.querySelectorAll(".dropdown-trigger")
    let dropdownOptions = {
        'hover': true,
        'constrainWidth': false
    }
    var instances = M.Dropdown.init(dropdowns, dropdownOptions)

    var sideNavs = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(sideNavs);

    let searchBar = document.getElementById('nav-search')
    searchBar.addEventListener('keyup', function (event) {
        if (event.keyCode === 13 && this.value) {
            let url = new URL(window.location)
            url.pathname = url.pathname.includes('bar') ? 'bar/query' : 'beer/query'
            let params = new URLSearchParams(url.search)
            let searchQuery = `(name:'${this.value}')`
            params.set('search', searchQuery)
            url.searchParams.set('search', params.get('search'))
            window.location.assign(url)
        }
    });
})