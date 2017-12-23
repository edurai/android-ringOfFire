package edray.ringoffire;

/**
 * Created by Ed Ray on 14.10.2017.
 */
public class Card {

    public int[] type = new int[13];

    public Card (int eightOrThirteen){
        for (int i = 0; i<eightOrThirteen; i++){
            this.type[i] = 4;
        }
    }
    public String effect(int i){

        switch(i) {
            case 0: return "Das wird wehtun! Du darfst 7 Schlucke selbst trinken.";
            case 1: return "Who knows me best? Stelle eine Frage über dich, alle in der Runde notieren ihre Antwort und zeigen sie gleichzeitig. Für jede korrekte Antwort trinkst du, für fast korrekte darf diejenige verteilen und für falsche trinken die entsprechenden Personen.";
            case 2: return "Wähle eine Kategorie. Der Letzte, dem nichts mehr dazu einfällt, muss trinken.";
            case 3: return "Jackpot! Verteile 10 Schlucke an beliebige Personen.";
            case 4: return "Zeig den Kommunisten in dir! Nenne ein Anzahl von Schlucken, die jeder inklusive dir trinken muss.";
            case 5: return "Du bist bestimmt wunderschön. Aber ab sofort wird dir niemand mehr in die Augen schauen. Falls doch, so trinkt derjenige.";
            case 6: return "Du bist bis zum Zug des nächsten Königs der Fragekönig. Stelle soviele Fragen, wie du willst, dir darf keiner Antworten. Falls doch, trinkt die betreffende Person.";
            case 7: return "Mach das Spiel lustiger! Denk dir eine Regel aus, die bei Missachtung zum Trinken führt.";
            case 8: return "Du hast ein Date! Wähle eine Person, die jedes Mal trinken muss, wenn du es tust.";
            case 9: return "Halt den Daumen bereit! Bis zur nächsten 3 darfst du zwischendurch den Daumen auf den Tisch legen. Der letzte, der es checkt, muss trinken.";
            case 10: return "Jetzt sind Rhymeskills angesagt. Nenne ein Wort, der nächste muss darauf reimen.";
            case 11: return "Alle Girls trinken";
            case 12: return "Alle Boys trinken";
            default: return "Hier ist was schief gelaufen.";
        }
    }
}
