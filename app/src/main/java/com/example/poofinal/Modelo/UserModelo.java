package com.example.poofinal.Modelo;

public class UserModelo {

    // Credenciales de ejemplo hardcodeadas
    private static final String USUARIO_VALIDO = "admin";
    private static final String CONTRASENA_VALIDA = "admin";

    // Método para validar el usuario y la contraseña
    public boolean validarUsuario(String usuario, String contrasena) {
        // Verificamos si las credenciales coinciden con las del usuario válido
        return USUARIO_VALIDO.equals(usuario) && CONTRASENA_VALIDA.equals(contrasena);
    }
}
