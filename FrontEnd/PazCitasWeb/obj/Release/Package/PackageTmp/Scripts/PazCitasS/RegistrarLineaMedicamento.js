let modalFormMedicamento;
let modalFormError;
function showModalFormMedicamento() {
    modalFormCliente = new bootstrap.Modal(document.getElementById('form-modal-medicamento'));
    modalFormCliente.show();
}
function showModalFormError() {
    modalFormError = new bootstrap.Modal(document.getElementById('errorModal'));
    modalFormError.show();
}
function hideModalFormMedicamento() {
    modalFormCliente.hide();
}