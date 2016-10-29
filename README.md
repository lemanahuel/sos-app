Cual es la funcionalidad de la app?

- Mostrar los cursos y workshops de coderhouse

Describir los métodos 

- SplashActivity
-- pasa despues de 3000

- LoginActivity
-- dos botones btnSubmit y btnSignin
-- btnSubmit consulta a la db y en caso de exito direcciona a MainActivity
-- btnSignin direcciona a SigninActivity

- SiginActivity
-- dos botones btnSubmit y btnLogin
-- btnSubmit consulta a la db y en caso de exito direcciona a LoginActivity
-- btnLogin direcciona a LoginActivity

- MainActivity
-- Creacion de Spinner de carreras, completado mediante careers.xml
-- Creacion de ListView de workshops, completado mediante workshops.xml
-- Registro de ContextMenu de workshops, completado mediante menu_ctx_workshop.xml
-- Registro de Toolbar mediante menu.xml y sus tres opciones
-- Seleccionando una opcion del spinner de carreras direcciona a CourseActivity

- CourseActivity
-- Recupera la carrera de la db y muestra sus niveles
-- Creacion de nivel, una vez creado recarga CourseActivity
-- Registro de Toolbar mediante menu.xml y sus tres opciones
-- Registro de ContextMenu de course, completado mediante menu_ctx_course.xml para poder habilitarlo y deshabilitarlo

- SettingsActivity
-- Persiste dos SharedPreferences mediante un RadioBtn y un CheckBtn
-- Guarda las preferencias
-- Registro de Toolbar mediante menu.xml y sus tres opciones

- AboutActivity
-- Muestra contenido hardcodeado
-- Registro de Toolbar mediante menu.xml y sus tres opciones

Diseño de Flow
