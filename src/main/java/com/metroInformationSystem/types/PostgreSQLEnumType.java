package com.metroInformationSystem.types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType {

    public void nullSafeSet(PreparedStatement statement,
                            Object value,
                            int index,
                            SharedSessionContractImplementor session
                            ) throws HibernateException, SQLException{
        if(value == null){
            statement.setNull(index, Types.OTHER);
        }else {
            statement.setObject(index, value.toString(), Types.OTHER);
        }
    }

}
