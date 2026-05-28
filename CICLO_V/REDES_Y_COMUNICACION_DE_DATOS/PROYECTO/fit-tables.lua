-- Pandoc Lua filter: fuerza a que todas las tablas tengan columnas con
-- anchos relativos que suman 1.0 (=100% del ancho disponible). Esto hace
-- que pandoc genere columnas p{...} en LaTeX, con wrap automatico,
-- en vez de columnas l (left, ancho natural) que se desbordan.

function Table(tbl)
  local n = #tbl.colspecs
  if n == 0 then return tbl end
  -- Asigna a cada columna un ancho relativo de 1/n.
  -- Pandoc interpreta esto como p{(1/n)*\textwidth} en LaTeX.
  for i = 1, n do
    local align = tbl.colspecs[i][1]
    tbl.colspecs[i] = {align, 1.0 / n}
  end
  return tbl
end
