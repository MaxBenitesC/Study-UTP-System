import re

with open("Articulo_Universitario_claude.md", "r", encoding="utf-8") as f:
    text = f.read()

# Remove the EV-XX — prefixes but keep the bold text
# e.g. **EV-01 — Captura Wireshark:** -> - **Captura Wireshark:**
# Only at the beginning of a line
text = re.sub(r'^(?:- )?\*\*EV-\d{2}\s*—\s*([^*]+)\*\*', r'- **\1**', text, flags=re.MULTILINE)

# Remove the **Estado:** PENDIENTE... to the end of the paragraph.
# This also removes extra sentences that belong to the meta-state.
text = re.sub(r'\*\*Estado:\*\*\s*(?:PENDIENTE|COMPLETADO).*?(?=\n\n|\n$)', '', text, flags=re.DOTALL)

with open("Articulo_Universitario_claude.md", "w", encoding="utf-8") as f:
    f.write(text)
print("Done")
