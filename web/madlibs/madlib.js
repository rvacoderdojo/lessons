function showStory(form) {

    for (var x = 0; x < form.elements.length; x++) {
        fillIn(form.elements[x]);
    }

    return false;
}

function fillIn(formField) {
    var name = formField.name;
    var value = formField.value;
    var placeholder = document.getElementById(name);
    if (placeholder) {
        placeholder.innerHTML=value;
    }
}
