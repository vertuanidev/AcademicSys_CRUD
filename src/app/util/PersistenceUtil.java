package app.util;

import javax.servlet.ServletContext;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Function;

public class PersistenceUtil {

    // salva uma DoublyLinkedList em um arquivo 
	// utilitário para salvar listas em arquivos de texto, encapsulando lógica de leitura e escrita de recursos

    public static <T> void saveList(ServletContext ctx,
                                    DoublyLinkedList<T> dll,
                                    Function<T, String> toLine,
                                    String resourcePath) throws IOException {
        String realPath = ctx.getRealPath(resourcePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(realPath))) {
            dll.forEach(item -> {
                try {
                    bw.write(toLine.apply(item));
                    bw.newLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }
}
