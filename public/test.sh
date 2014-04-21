python -m SimpleHTTPServer & P=$!
trap "kill $P" INT EXIT
python -m webbrowser "http://localhost:8000"
wait