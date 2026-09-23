#!/usr/bin/env bash
# ==============================================================================
# Script de sincronización de documentos académicos hacia Google Drive
# Responsable: Max Anderson Benites Corazón - Senior Technical Implementation Specialist
# Herramienta: rclone
# ==============================================================================
set -euo pipefail

REMOTE_NAME="GoogleDrive"
EXTRA_ARGS=()

for arg in "$@"; do
    if [[ "$arg" != -* && "$REMOTE_NAME" == "GoogleDrive" && "$arg" != "GoogleDrive" ]]; then
        REMOTE_NAME="$arg"
    else
        EXTRA_ARGS+=("$arg")
    fi
done

DRIVE_FOLDER="UTP_Documentos_Backup"
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "=== Iniciando respaldo de documentos a Google Drive ($REMOTE_NAME:$DRIVE_FOLDER) ==="
echo "Directorio base: $PROJECT_DIR"
echo "Filtro: Documentos (PDF, Office), multimedia (MP4, audio) y comprimidos"
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
    --include "*.mp4" \
    --include "*.mkv" \
    --include "*.avi" \
    --include "*.mov" \
    --include "*.webm" \
    --include "*.mp3" \
    --include "*.wav" \
    --include "*.m4a" \
    --progress \
    --transfers=4 \
    --checkers=8 \
    ${EXTRA_ARGS[@]+"${EXTRA_ARGS[@]}"}

echo "------------------------------------------------------------------------------"
echo "✅ Respaldo a Google Drive finalizado con éxito."
