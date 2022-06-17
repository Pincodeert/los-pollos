package com.example.lospollos.exceptions;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

// 2 constructors en laten overerven naar de parent dmv super()

    // 1) een lege constructor
    public RecordNotFoundException() {
        super();
    }

    // 2) een constructor met een message
    public RecordNotFoundException(String message){
        super(message);
    }
}