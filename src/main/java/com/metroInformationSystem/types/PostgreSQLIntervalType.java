package com.metroInformationSystem.types;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGInterval;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PostgreSQLIntervalType implements UserType {

    public static final PostgreSQLIntervalType INSTANCE = new PostgreSQLIntervalType();



    @Override
    public int[] sqlTypes() {
        return new int[]{StringType.INSTANCE.sqlType()};
    }

    @Override
    public Class returnedClass() {
        return PostgreSQLIntervalType.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equals(x,y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {

        String interval = rs.getString(names[0]);
        if(interval != null){

            String[] buf = interval.split(" ");
            return new PGInterval(
                    Integer.parseInt(buf[0]),
                    Integer.parseInt(buf[2]),
                    Integer.parseInt(buf[4]),
                    Integer.parseInt(buf[6]),
                    Integer.parseInt(buf[8]),
                    Double.parseDouble(buf[10]));
        }

        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null){
            st.setNull(index, StringType.INSTANCE.sqlType());
        }else {
            PGInterval interval = (PGInterval) value;
            st.setString(index,
                    interval.getYears() + " year " +
                    interval.getMonths() + " months " +
                    interval.getDays() + " days " +
                    interval.getHours() + " hours " +
                    interval.getMinutes() + " minutes " +
                    interval.getSeconds() + " seconds "
            );
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
