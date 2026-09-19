# Demostración de arreglos en JavaScript

Proyecto preparado para mostrar en clase:

- Arreglos densos.
- Arreglos no densos (`sparse`).
- `shift()`, `unshift()`, `push()` y `pop()`.

## Ejecución

Desde la raíz de este proyecto:

```bash
npm run densos
npm run no-densos
npm run metodos
```

Para ejecutar las tres demostraciones seguidas:

```bash
npm start
```

No es necesario instalar dependencias. Para comprobar los ejemplos:

```bash
npm test
```

## Terminal inferior en Neovim

Abrir el proyecto:

```bash
nvim .
```

Dentro de Neovim, abrir una terminal inferior:

```vim
:botright 15split | terminal
```

Presionar `i` y ejecutar uno de los comandos `npm run ...`. Para regresar al
modo normal: `Ctrl+\\`, seguido de `Ctrl+n`.
