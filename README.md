# N-gram Text Analyzer (Java)

Small Java program that reads multiple text files and produces **1-gram, 2-gram, and 3-gram** frequency counts, printing the **Top‑N** results and writing a short report.

## Features
- UTF‑8 safe I/O for multilingual corpora (e.g., Turkish + English).
- Configurable n‑gram sizes (currently 1, 2, 3 in `Main.java`).
- Simple tokenization with punctuation/whitespace splitting.
- Top‑N listing for each file and each n‑gram size.
- Sample outputs in `reports/report.txt`.

## Repo Structure
```
.
├── src/                  # Java source (Main.java)
├── assets/               # Screenshots / figures (nlp1.png, nlp2.png)
├── reports/              # Example output (report.txt)
├── BİLİM İŞ BAŞINDA.txt  # Sample dataset files (kept at repo root intentionally)
├── grimms-fairy-tales_*.txt
└── .gitignore
```

> **Why are the `.txt` files in the repo root?**  
> The provided `Main.java` references the files by **relative name** (e.g., `"grimms-fairy-tales_1.txt"`).  
> To run without code changes, they are kept at the repo root. If you prefer `data/`, change the file paths in `Main.java` accordingly.

## Requirements
- Java 17+ (recommended).  
  Works with older JDKs that support `var` removal (if needed, adjust syntax).

## Build & Run (no build tool)
```bash
# From repo root
javac -encoding UTF-8 -d out src/Main.java
java -Dfile.encoding=UTF-8 -cp out Main
```

- `-encoding UTF-8` ensures Turkish characters are read/written correctly.
- `-Dfile.encoding=UTF-8` pins runtime to UTF‑8.

## Example Output
See `reports/report.txt` for a sample run (Top‑N per file and per n‑gram size).
