package edray.ringoffire;

/**
 * Created by Ed Ray on 14.10.2017.
 */

public class Card {

    // Array containing all 13 card types
    public int[] type = new int[13];

    // Card gets initialized with either 8 or 13 types, depending on game mode
    // Every type has 4 different cards (heart, spades, check, cross)
    public Card (int eightOrThirteen){
        for (int i = 0; i<eightOrThirteen; i++){
            this.type[i] = 4;
        }
    }

    // Default function for giving every card a rule
    public String effect(int i){

        switch(i) {
            // 7
            case 0: return "Das wird wehtun! Du darfst 7 Schlucke selbst trinken.";
            // 8
            case 1: return "Who knows me best? Stelle eine Frage über dich, alle in der Runde notieren ihre Antwort und zeigen sie gleichzeitig. Für jede korrekte Antwort trinkst du, für fast korrekte darf diejenige verteilen und für falsche trinken die entsprechenden Personen.";
            // 9
            case 2: return "Wähle eine Kategorie. Der Letzte, dem nichts mehr dazu einfällt, muss trinken.";
            // 10
            case 3: return "Jackpot! Verteile 10 Schlucke an beliebige Personen.";
            // J
            case 4: return "Zeig den Kommunisten in dir! Nenne ein Anzahl von Schlucken, die jeder inklusive dir trinken muss.";
            // Q
            case 5: return "Du bist bestimmt wunderschön. Aber ab sofort wird dir niemand mehr in die Augen schauen. Falls doch, so trinkt derjenige.";
            // K
            case 6: return "Du bist bis zum Zug des nächsten Königs der Fragekönig. Stelle soviele Fragen, wie du willst, dir darf keiner Antworten. Falls doch, trinkt die betreffende Person.";
            // A
            case 7: return "Mach das Spiel lustiger! Denk dir eine Regel aus, die bei Missachtung zum Trinken führt.";
            // 2
            case 8: return "Du hast ein Date! Wähle eine Person, die jedes Mal trinken muss, wenn du es tust.";
            // 3
            case 9: return "Halt den Daumen bereit! Bis zur nächsten 3 darfst du zwischendurch den Daumen auf den Tisch legen. Der letzte, der es checkt, muss trinken.";
            // 4
            case 10: return "Jetzt sind Rhymeskills angesagt. Nenne ein Wort, der nächste muss darauf reimen.";
            // 5
            case 11: return "Alle Girls trinken";
            // 6
            case 12: return "Alle Boys trinken";
            // Should not happen
            default: return "Hier ist was schief gelaufen.";
        }
    }
}
