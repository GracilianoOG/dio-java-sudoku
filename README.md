# 👨🏻‍💻 Jogo de Sudoku (Desafio da DIO)

## 📖 Descrição

_Este projeto se trata de um jogo de Sudoku desenvolvido em Java com a biblioteca gráfica Swing. O desafio foi proposto durante o bootcamp de Java da Digital Innovation One (DIO)._

O jogo está disponível em forma de argumentos para a aplicação Java. O formato segue da seguinte maneira:

`coluna,linha;valor_esperado,eh_valor_fixo`

Foi muito gratificante desenvolver esse projeto, pois nunca havia desenvolvido um jogo em Java antes. Tive a oportunidade de aprender mais sobre a linguagem, como o uso de _collections_ e _stream API_ em um projeto prático.

## 📒 Aprendizado

Também aprendi um pouco sobre o padrão de projeto `observer` e como ele pode ser aplicado em uma aplicação Java. Por exemplo, no projeto atual, o _observer_ é aplicado da seguinte forma:

```java
// Um enum é criado para armazenar os tipos do evento
public enum EventEnum {
    CLEAR_SPACE
}
```

```java
// Uma interface é criada para definir a implementação dos métodos do listener
public interface EventListener {
    void update(EventEnum eventType);
}
```

```java
public class NotifierService {
    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
        put(EventEnum.CLEAR_SPACE, new ArrayList<>());
    }};

    // Método que inscreve os objetos para serem notificados
    public void subscribe(EventEnum eventType, EventListener listener) {
        List<EventListener> subscribedListeners = listeners.get(eventType);
        subscribedListeners.add(listener);
    }

    // Método que notifica os objetos, executando o método update da interface
    public void notify(EventEnum eventType) {
        listeners.get(eventType).forEach(l -> l.update(eventType));
    }
}
```

Cada célula do _sudoku_ é inscrita no _notifier_:

```java
fields.forEach(f -> notifierService.subscribe(EventEnum.CLEAR_SPACE, f));
```

E quando algo deve acontecer, os objetos de determinado tipo são notificados:

```java
notifierService.notify(EventEnum.CLEAR_SPACE);
```

Concluíndo, desenvolver esse jogo foi gratificante, pois me permitiu entender melhor certas peculiaridades da linguagem de forma prática.

## 🗂️ Instalação local

1. Clone este repositório:

```bash
git clone https://github.com/GracilianoOG/dio-java-sudoku.git
```

2. Abra o projeto em sua IDE preferida, exemplo no VSCode:

```bash
cd dio-java-sudoku/
```

3. Após navegar para dentro do diretório, inicie o VSCode:

```base
code .
```

4. Utilize os seguintes valores como argumento para a aplicação Java (jogo disponível para o Sudoku):

```
0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false
```

## 🛠️ Ferramentas e tecnologias

[![Ferramentas](https://skillicons.dev/icons?i=java,idea)](https://skillicons.dev)

- Desenvolvido na linguagem Java.
- Visual criado com Java Swing (UI).
- Criado com a IDE Intellij IDEA Community.

## 🔗 Links

- [Digital Innovation One](https://www.dio.me/)

## 🧑🏻‍💻 Autor

- LinkedIn: [@gabrielgmbarros](https://www.linkedin.com/in/gabrielgmbarros)
- GitHub: [@GracilianoOG](https://github.com/GracilianoOG)

## 📝 Licença

O código fonte está sob a licença [MIT](./LICENSE).
