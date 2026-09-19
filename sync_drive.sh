#!/usr/bin/env bash
# ==============================================================================
# Script de sincronización de documentos académicos hacia Google Drive
# Responsable: Max Anderson Benites Corazón - Senior Technical Implementation Specialist
# Herramienta: rclone
# ==============================================================================
set -euo pipefail

REMOTE_NAME="${1:-gdrive}"
DRIVE_FOLDER="UTP_Documentos_Backup"
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "=== Iniciando respaldo de documentos a Google Drive ($REMOTE_NAME:$DRIVE_FOLDER) ==="
echo "Directorio base: $PROJECT_DIR"
echo "Filtro: PDF, DOCX, PPTX, XLSX, comprimidos"
echo "Estrategia: 'copy' (Preserva archivos en Drive aunque los borres localmente)"
echo "------------------------------------------------------------------------------"

rclone copy "$PROJECT_DIR" "$REMOTE_NAME:$DRIVE_FOLDER" \
    --include "*.pdf" \
    --include "*.docx" \
    --include "*.doc" \
    --include "*.pptx" \
    --include "*.ppt" \
    --include "*.xlsx" \
    --include "*.xls" \
    --include "*.zip" \
    --include "*.rar" \
    --include "*.7z" \
    --progress \
    --transfers=4 \
    --checkers=8

echo "------------------------------------------------------------------------------"
echo "✅ Respaldo a Google Drive finalizado con éxito."
