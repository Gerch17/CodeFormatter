package refactor;

import broker.Broker;
import exceptions.InvalidCodeException;
import parser.Parser;

public class Refactor {

    public String doRefactor(String code) {
        Broker broker = new Broker();
        Parser parser = new Parser(broker.receiveFile(code));
        try {
            return broker.returnResult(parser.makeItClear());
        } catch (InvalidCodeException e) {
            e.printStackTrace();
        }
        return code;
    }
}
