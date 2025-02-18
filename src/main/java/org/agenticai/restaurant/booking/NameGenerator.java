package org.agenticai.restaurant.booking;

public class NameGenerator {

    private static final String[] ADJECTIVES = {
            "admiring", "adoring", "agitated", "amazing", "angry", "awesome", "blissful",
            "boring", "brave", "clever", "compassionate", "competent", "condescending",
            "confident", "cranky", "dazzling", "determined", "distracted", "dreamy",
            "eager", "ecstatic", "elastic", "elated", "elegant", "eloquent", "epic",
            "fervent", "festive", "flamboyant", "focused", "friendly", "frosty",
            "gallant", "gifted", "goofy", "gracious", "hardcore", "heuristic",
            "hopeful", "hungry", "infallible", "inspiring", "intelligent", "interesting",
            "jolly", "jovial", "keen", "kind", "laughing", "loving", "lucid",
            "mystifying", "modest", "musing", "naughty", "nervous", "nice", "nifty",
            "nostalgic", "objective", "optimistic", "peaceful", "pedantic",
            "pensive", "practical", "priceless", "quirky", "quizzical", "recursing",
            "relaxed", "reverent", "romantic", "sad", "serene", "sharp",
            "silly", "sleepy", "stoic", "stupefied", "suspicious", "sweet",
            "tender", "thirsty", "trusting", "unruffled", "upbeat", "vibrant",
            "vigilant", "vigorous", "wizardly", "wonderful", "xenodochial",
            "youthful", "zealous", "zen"
    };

    private static final String[] SCIENTISTS = {
            "albattani", "allen", "almeida", "antonelli", "agnesi", "archimedes",
            "ardinghelli", "aryabhata", "austin", "babbage", "banach", "bardeen",
            "bartik", "bassi", "beaver", "bell", "benz", "bhabha", "bhaskara",
            "blackwell", "bohr", "booth", "borg", "bose", "bouman", "boyd",
            "brahmagupta", "brattain", "brown", "carson", "chandrasekhar",
            "chaplygin", "chatelet", "chatterjee", "chebyshev", "clarke",
            "colden", "cori", "cray", "curie", "darwin", "davinci", "dewdney",
            "dhawan", "diffie", "dijkstra", "dirac", "driscoll", "dubinsky",
            "easley", "edison", "einstein", "elbakyan", "elgamal", "elion",
            "engelbart", "euclid", "euler", "faraday", "fermat", "fermi",
            "feynman", "franklin", "gagarin", "galileo", "galois", "ganguly",
            "gates", "gauss", "germain", "goldberg", "goldstine", "goldwasser",
            "golick", "goodall", "gould", "greider", "grothendieck", "haibt",
            "hamilton", "hawking", "heisenberg", "hellman", "hermann", "herschel",
            "hertz", "hodgkin", "hofstadter", "hoover", "hopper", "hugle",
            "hypatia", "ishizaka", "jackson", "jang", "jennings", "jepsen",
            "johnson", "joliot", "jones", "kalam", "kapitsa", "kare", "keldysh",
            "keller", "kepler", "khayyam", "khorana", "kilby", "kirch", "knuth",
            "kowalevski", "lalande", "lamarr", "lamport", "leakey", "leavitt",
            "lederberg", "lehmann", "lewin", "lichterman", "liskov", "lovelace",
            "lumiere", "mahavira", "margulis", "matsumoto", "maxwell", "mayer",
            "mccarthy", "mcclintock", "mclean", "mcnulty", "mendel", "mendeleev",
            "meitner", "meninsky", "merkle", "mestorf", "minsky", "mirzakhani",
            "montalcini", "moore", "morse", "murdock", "neumann", "newton",
            "nightingale", "nobel", "noether", "northcutt", "noyce", "panini",
            "pare", "pascal", "pasteur", "payne", "perlman", "pike", "poincare",
            "poitras", "proskuriakova", "ptolemy", "raman", "ramanujan",
            "ride", "ritchie", "roentgen", "rosalind", "rubin", "saha",
            "sammet", "sanderson", "shamir", "shannon", "shaw", "shirley",
            "shockley", "sinoussi", "snyder", "solomon", "spence", "stonebraker",
            "swanson", "swartz", "swirles", "taussig", "tereshkova", "tesla",
            "tharp", "thompson", "torvalds", "tu", "turing", "varahamihira",
            "vaughan", "visvesvaraya", "volhard", "wescoff", "wiles", "williams",
            "wilson", "wing", "wozniak", "wright", "wu", "yalow", "yonath"
    };

    public static String generate() {
        return ADJECTIVES[(int) (Math.random() * ADJECTIVES.length)] + "_" + SCIENTISTS[(int) (Math.random() * SCIENTISTS.length)];
    }


}
