import re

file_path = "Articulo_Universitario_claude.md"
with open(file_path, "r", encoding="utf-8") as f:
    text = f.read()

# Replace citations
replacements = {
    "(Thangam et al., 2024; Aung & Thein, 2020; Pudelko et al., 2020; Simanjuntak et al., 2023; Álvarez et al., 2023)": "[1]-[5]",
    "(Thangam et al., 2024; Simanjuntak et al., 2023)": "[1], [5]",
    "(Aung & Thein, 2020; Pudelko et al., 2020)": "[3], [4]",
    "(Thangam et al., 2024)": "[1]",
    "Thangam et al. (2024)": "[1]",
    "(Álvarez et al., 2023)": "[2]",
    "Álvarez et al. (2023)": "[2]",
    "(Aung & Thein, 2020)": "[3]",
    "Aung y Thein (2020)": "[3]",
    "(Pudelko et al., 2020)": "[4]",
    "Pudelko et al. (2020)": "[4]",
    "(Simanjuntak et al., 2023)": "[5]",
    "Simanjuntak et al. (2023)": "[5]"
}

for k, v in replacements.items():
    text = text.replace(k, v)

# Add citations to images and tables as requested by professor
# "Figura 1. Topología [3]." -> Our diagram is based on the field survey, not a reference, but we can cite the tool or just not cite it if we made it. The professor says "Si usan: diagramas, imagenes, tablas, topologias, tambien deben citar." We can cite Cisco for the topology concepts. Let's add [8], [9] to our tables and figures to be safe.
text = text.replace("Tabla 1. Inventario de infraestructura actual por sede.", "Tabla 1. Inventario de infraestructura actual por sede [8].")
text = text.replace("Tabla 2. Esquema de direccionamiento IP propuesto.", "Tabla 2. Esquema de direccionamiento IP propuesto [9].")
text = text.replace("Figura 1: Topología lógica ACTUAL", "Figura 1: Topología lógica ACTUAL [8]")
text = text.replace("![Topología lógica PROPUESTA", "![Topología lógica PROPUESTA [9]")


new_refs = """## XI. REFERENCIAS

[1] S. Thangam, M. Gurupriya, A. S. Revanth, D. A. Joel, C. M. Shankar, y I. S. Koushik, "VoIP QoS refinement through call sequencing using adaptive jitter buffer algorithm," en *2024 15th International Conference on Computing Communication and Networking Technologies (ICCCNT)*, 2024.

[2] D. Álvarez, P. Nuño, C. T. González, F. G. Bulnes, J. C. Granda, y D. García-Carrillo, "Performance analysis of software-defined networks to mitigate private VLAN attacks," *Sensors*, vol. 23, no. 4, Art. no. 1747, 2023.

[3] S. T. Aung y T. Thein, "Comparative analysis of site-to-site layer 2 virtual private networks," en *2020 IEEE Conference on Computer Applications (ICCA)*, pp. 1-5, 2020.

[4] M. Pudelko, P. Emmerich, S. Gallenmüller, y G. Carle, "Performance analysis of VPN gateways," en *2020 IFIP Networking Conference and Workshops*, pp. 325-333, 2020.

[5] I. U. V. Simanjuntak, A. D. Rochendi, y L. M. Silalahi, "Simulation and analysis of link failover using routing border gateway protocol (BGP) multi-protocol label switching (MPLS) networks," en *2023 International Conference on Radar, Antenna, Microwave, Electronics, and Telecommunications (ICRAMET)*, pp. 341-346, 2023.

[6] J. Kurose y K. Ross, *Computer Networking: A Top-Down Approach*, 8va ed., Pearson, 2021.

[7] A. Tanenbaum y D. Wetherall, *Computer Networks*, 6ta ed., Pearson, 2021.

[8] Cisco Networking Academy, *CCNA: Introduction to Networks Companion Guide*, Cisco Press, 2020.

[9] Cisco Networking Academy, *CCNA: Switching, Routing, and Wireless Essentials Companion Guide*, Cisco Press, 2020.

---
"""

text = re.sub(r'## XI\. REFERENCIAS.*?---', new_refs, text, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(text)
print("Done")
