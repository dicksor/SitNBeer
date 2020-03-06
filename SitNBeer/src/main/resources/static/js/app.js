document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.dropdown-trigger');
    let dropdownOptions = {
        'hover': true,
        'constrainWidth': false
    }
    var instances = M.Dropdown.init(elems, dropdownOptions);
});