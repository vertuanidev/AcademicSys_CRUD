package app.model;

// Usado para transportar resultados de médias e aprovação entre Servlets e JSPs
public class Result {
    private final String name;
    private final float media;
    private final boolean aprovado;

    public Result(String name, float media, boolean aprovado) {
        this.name = name;
        this.media = media;
        this.aprovado = aprovado;
    }

    public String getName()    { return name;      }
    public float  getMedia()   { return media;     }
    public boolean isAprovado(){ return aprovado; }
}
