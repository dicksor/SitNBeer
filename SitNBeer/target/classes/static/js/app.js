document.addEventListener('DOMContentLoaded', function () {
    var ranges  = document.querySelectorAll("input[type=range]");
    M.Range.init(ranges);

    var dropdowns = document.querySelectorAll('.dropdown-trigger')
    let dropdownOptions = {
        'hover': true,
        'constrainWidth': false
    }
    var instances = M.Dropdown.init(dropdowns, dropdownOptions)

    var sideNavs = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(sideNavs);
})