GRN='\033[0;32m'
RED='\033[0;31m'
CYN='\033[0;36m'
WHT='\033[1;37m'
DIM='\033[2;37m'
RST='\033[0m'

SOURCE="/home/master/programacao/projetos/pfs-v2.0/source"
ROOT="/home/master/programacao/projetos/pfs-v2.0"
CLASSES="$ROOT/class-files"

clear
echo ""
printf "${DIM}  ╔══════════════════════════════════════╗${RST}\n"
printf "${DIM}  ║${RST}  ${CYN}PFS v2.0 — Build System${RST}             ${DIM}║${RST}\n"
printf "${DIM}  ╚══════════════════════════════════════╝${RST}\n"
echo ""

# ── compilar ────────────────────────────────────────────────
printf "  ${DIM}▶ Compilando fontes em:${RST} ${WHT}$SOURCE${RST}\n\n"

cd "$SOURCE" || { printf "  ${RED}✗ Directório source não encontrado${RST}\n"; exit 1; }

javac *.java 2>"$ROOT/build.log"

if [ $? -ne 0 ]; then
    printf "  ${RED}✗ Compilação falhou. Erros:${RST}\n\n"
    while IFS= read -r line; do
        printf "    ${RED}│${RST} %s\n" "$line"
    done < "$ROOT/build.log"
    echo ""
    exit 1
fi

printf "  ${GRN}✓ Compilação concluída sem erros${RST}\n\n"

# ── mover .class ─────────────────────────────────────────────
printf "  ${DIM}▶ Movendo .class para:${RST} ${WHT}$CLASSES${RST}\n"

cd "$ROOT" || exit 1
rm -f "$CLASSES"/*.class
mv "$SOURCE"/*.class "$CLASSES"/

CLASS_COUNT=$(ls "$CLASSES"/*.class 2>/dev/null | wc -l)
printf "  ${GRN}✓ $CLASS_COUNT ficheiro(s) .class movidos${RST}\n\n"

# ── executar ─────────────────────────────────────────────────
printf "  ${DIM}▶ Executando Main...${RST}\n"
printf "${DIM}  ────────────────────────────────────────${RST}\n\n"

cd "$CLASSES" || exit 1
java Main

if [ $? -ne 0 ]; then
    echo ""
    printf "  ${RED}✗ Programa terminou com erro (código $?)${RST}\n"
    printf "${DIM}  ────────────────────────────────────────${RST}\n"
    echo ""
    exit 1
fi

echo ""
printf "  ${GRN}✓ Programa concluído com sucesso${RST}\n"
printf "${DIM}  ────────────────────────────────────────${RST}\n"
echo ""
