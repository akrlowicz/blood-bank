package edu.ib;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.RowSetWarning;
import javax.sql.rowset.spi.SyncProvider;
import javax.sql.rowset.spi.SyncProviderException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

/**
 * A simple {@link CachedRowSet} wrapper.
 * Uses {@link CachedRowSetImpl} on Java 1.6.
 * Avoids direct {@link CachedRowSetImpl} usage on Java 1.7+
 * (especially on Java 9 which restricts access to <em>com.sun.rowset</em> package).
 *
 * @see CachedRowSet
 */
public class CachedRowSetWrapper implements CachedRowSet, Serializable {

    /**
     * Serialization version number.
     */
    private static final long serialVersionUID = 8082871764670248245L;

    /**
     * Underlying CachedRowSet.
     */
    private final CachedRowSet internalRowSet;

    /**
     * Create a new instance of a CachedRowSetWrapper.
     * <p>
     * Using {@link CachedRowSetImpl} on Java 1.6.
     * <p>
     * Using {@link RowSetProvider} on Java 1.7+.
     */


    public CachedRowSetWrapper() throws SQLException {
        String javaVersion = System.getProperty("java.version");

        if (javaVersion != null && javaVersion.startsWith("1.6.")) {
            internalRowSet = new CachedRowSetImpl();
        } else {
            internalRowSet = RowSetProvider.newFactory().createCachedRowSet();
        }
    }

    @Override
    public void populate(ResultSet data) throws SQLException {
        internalRowSet.populate(data);
    }

    @Override
    public void execute(Connection conn) throws SQLException {
        internalRowSet.execute(conn);
    }

    @Override
    public void acceptChanges() throws SyncProviderException {
        internalRowSet.acceptChanges();
    }

    @Override
    public void acceptChanges(Connection con) throws SyncProviderException {
        internalRowSet.acceptChanges(con);
    }

    @Override
    public void restoreOriginal() throws SQLException {
        internalRowSet.restoreOriginal();
    }

    @Override
    public void release() throws SQLException {
        internalRowSet.release();
    }

    @Override
    public void undoDelete() throws SQLException {
        internalRowSet.undoDelete();
    }

    @Override
    public void undoInsert() throws SQLException {
        internalRowSet.undoInsert();
    }

    @Override
    public void undoUpdate() throws SQLException {
        internalRowSet.undoUpdate();
    }

    @Override
    public boolean columnUpdated(int idx) throws SQLException {
        return internalRowSet.columnUpdated(idx);
    }

    @Override
    public boolean columnUpdated(String columnName) throws SQLException {
        return internalRowSet.columnUpdated(columnName);
    }

    @Override
    public Collection<?> toCollection() throws SQLException {
        return internalRowSet.toCollection();
    }

    @Override
    public Collection<?> toCollection(int column) throws SQLException {
        return internalRowSet.toCollection(column);
    }

    @Override
    public Collection<?> toCollection(String column) throws SQLException {
        return internalRowSet.toCollection(column);
    }

    @Override
    public SyncProvider getSyncProvider() throws SQLException {
        return internalRowSet.getSyncProvider();
    }

    @Override
    public void setSyncProvider(String provider) throws SQLException {
        internalRowSet.setSyncProvider(provider);
    }

    @Override
    public int size() {
        return internalRowSet.size();
    }

    @Override
    public void setMetaData(RowSetMetaData md) throws SQLException {
        internalRowSet.setMetaData(md);
    }

    @Override
    public ResultSet getOriginal() throws SQLException {
        return internalRowSet.getOriginal();
    }

    @Override
    public ResultSet getOriginalRow() throws SQLException {
        return internalRowSet.getOriginalRow();
    }

    @Override
    public void setOriginalRow() throws SQLException {
        internalRowSet.setOriginalRow();
    }

    @Override
    public String getTableName() throws SQLException {
        return internalRowSet.getTableName();
    }

    @Override
    public void setTableName(String tabName) throws SQLException {
        internalRowSet.setTableName(tabName);
    }

    @Override
    public int[] getKeyColumns() throws SQLException {
        return internalRowSet.getKeyColumns();
    }

    @Override
    public void setKeyColumns(int[] keys) throws SQLException {
        internalRowSet.setKeyColumns(keys);
    }

    @Override
    public RowSet createShared() throws SQLException {
        return internalRowSet.createShared();
    }

    @Override
    public CachedRowSet createCopy() throws SQLException {
        return internalRowSet.createCopy();
    }

    @Override
    public CachedRowSet createCopySchema() throws SQLException {
        return internalRowSet.createCopySchema();
    }

    @Override
    public CachedRowSet createCopyNoConstraints() throws SQLException {
        return internalRowSet.createCopyNoConstraints();
    }

    @Override
    public RowSetWarning getRowSetWarnings() throws SQLException {
        return internalRowSet.getRowSetWarnings();
    }

    @Override
    public boolean getShowDeleted() throws SQLException {
        return internalRowSet.getShowDeleted();
    }

    @Override
    public void setShowDeleted(boolean b) throws SQLException {
        internalRowSet.setShowDeleted(b);
    }

    @Override
    public void commit() throws SQLException {
        internalRowSet.commit();
    }

    @Override
    public void rollback() throws SQLException {
        internalRowSet.rollback();
    }

    @Override
    public void rollback(Savepoint s) throws SQLException {
        internalRowSet.rollback(s);
    }

    @Override
    public void rowSetPopulated(RowSetEvent event, int numRows) throws SQLException {
        internalRowSet.rowSetPopulated(event, numRows);
    }

    @Override
    public void populate(ResultSet rs, int startRow) throws SQLException {
        internalRowSet.populate(rs, startRow);
    }

    @Override
    public void setPageSize(int size) throws SQLException {
        internalRowSet.setPageSize(size);
    }

    @Override
    public int getPageSize() {
        return internalRowSet.getPageSize();
    }

    @Override
    public boolean nextPage() throws SQLException {
        return internalRowSet.nextPage();
    }

    @Override
    public boolean previousPage() throws SQLException {
        return internalRowSet.previousPage();
    }

    @Override
    public String getUrl() throws SQLException {
        return internalRowSet.getUrl();
    }

    @Override
    public void setUrl(String url) throws SQLException {
        internalRowSet.setUrl(url);
    }

    @Override
    public String getDataSourceName() {
        return internalRowSet.getDataSourceName();
    }

    @Override
    public void setDataSourceName(String name) throws SQLException {
        internalRowSet.setDataSourceName(name);
    }

    @Override
    public String getUsername() {
        return internalRowSet.getUsername();
    }

    @Override
    public void setUsername(String name) throws SQLException {
        internalRowSet.setUsername(name);
    }

    @Override
    public String getPassword() {
        return internalRowSet.getPassword();
    }

    @Override
    public void setPassword(String password) throws SQLException {
        internalRowSet.setPassword(password);
    }

    @Override
    public int getTransactionIsolation() {
        return internalRowSet.getTransactionIsolation();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        internalRowSet.setTransactionIsolation(level);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return internalRowSet.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        internalRowSet.setTypeMap(map);
    }

    @Override
    public String getCommand() {
        return internalRowSet.getCommand();
    }

    @Override
    public void setCommand(String cmd) throws SQLException {
        internalRowSet.setCommand(cmd);
    }

    @Override
    public boolean isReadOnly() {
        return internalRowSet.isReadOnly();
    }

    @Override
    public void setReadOnly(boolean value) throws SQLException {
        internalRowSet.setReadOnly(value);
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return internalRowSet.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        internalRowSet.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return internalRowSet.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        internalRowSet.setMaxRows(max);
    }

    @Override
    public boolean getEscapeProcessing() throws SQLException {
        return internalRowSet.getEscapeProcessing();
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        internalRowSet.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return internalRowSet.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        internalRowSet.setQueryTimeout(seconds);
    }

    @Override
    public void setType(int type) throws SQLException {
        internalRowSet.setType(type);
    }

    @Override
    public void setConcurrency(int concurrency) throws SQLException {
        internalRowSet.setConcurrency(concurrency);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        internalRowSet.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        internalRowSet.setNull(parameterName, sqlType);
    }

    @Override
    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
        internalRowSet.setNull(paramIndex, sqlType, typeName);
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        internalRowSet.setNull(parameterName, sqlType, typeName);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        internalRowSet.setBoolean(parameterIndex, x);
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        internalRowSet.setBoolean(parameterName, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        internalRowSet.setByte(parameterIndex, x);
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        internalRowSet.setByte(parameterName, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        internalRowSet.setShort(parameterIndex, x);
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        internalRowSet.setShort(parameterName, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        internalRowSet.setInt(parameterIndex, x);
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        internalRowSet.setInt(parameterName, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        internalRowSet.setLong(parameterIndex, x);
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        internalRowSet.setLong(parameterName, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        internalRowSet.setFloat(parameterIndex, x);
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        internalRowSet.setFloat(parameterName, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        internalRowSet.setDouble(parameterIndex, x);
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        internalRowSet.setDouble(parameterName, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        internalRowSet.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        internalRowSet.setBigDecimal(parameterName, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        internalRowSet.setString(parameterIndex, x);
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        internalRowSet.setString(parameterName, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        internalRowSet.setBytes(parameterIndex, x);
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        internalRowSet.setBytes(parameterName, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        internalRowSet.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        internalRowSet.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        internalRowSet.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        internalRowSet.setTimestamp(parameterName, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        internalRowSet.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        internalRowSet.setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        internalRowSet.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        internalRowSet.setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        internalRowSet.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        internalRowSet.setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        internalRowSet.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        internalRowSet.setAsciiStream(parameterName, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        internalRowSet.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        internalRowSet.setBinaryStream(parameterName, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        internalRowSet.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        internalRowSet.setCharacterStream(parameterName, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        internalRowSet.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        internalRowSet.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        internalRowSet.setObject(parameterName, x, targetSqlType, scale);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        internalRowSet.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        internalRowSet.setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        internalRowSet.setObject(parameterName, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        internalRowSet.setObject(parameterIndex, x);
    }

    @Override
    public void setRef(int i, Ref x) throws SQLException {
        internalRowSet.setRef(i, x);
    }

    @Override
    public void setBlob(int i, Blob x) throws SQLException {
        internalRowSet.setBlob(i, x);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        internalRowSet.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        internalRowSet.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        internalRowSet.setBlob(parameterName, inputStream, length);
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        internalRowSet.setBlob(parameterName, x);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        internalRowSet.setBlob(parameterName, inputStream);
    }

    @Override
    public void setClob(int i, Clob x) throws SQLException {
        internalRowSet.setClob(i, x);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        internalRowSet.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        internalRowSet.setClob(parameterIndex, reader);
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        internalRowSet.setClob(parameterName, reader, length);
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        internalRowSet.setClob(parameterName, x);
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        internalRowSet.setClob(parameterName, reader);
    }

    @Override
    public void setArray(int i, Array x) throws SQLException {
        internalRowSet.setArray(i, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        internalRowSet.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        internalRowSet.setDate(parameterName, x);
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        internalRowSet.setDate(parameterName, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        internalRowSet.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        internalRowSet.setTime(parameterName, x);
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        internalRowSet.setTime(parameterName, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        internalRowSet.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        internalRowSet.setTimestamp(parameterName, x, cal);
    }

    @Override
    public void clearParameters() throws SQLException {
        internalRowSet.clearParameters();
    }

    @Override
    public void execute() throws SQLException {
        internalRowSet.execute();
    }

    @Override
    public void addRowSetListener(RowSetListener listener) {
        internalRowSet.addRowSetListener(listener);
    }

    @Override
    public void removeRowSetListener(RowSetListener listener) {
        internalRowSet.removeRowSetListener(listener);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        internalRowSet.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        internalRowSet.setSQLXML(parameterName, xmlObject);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        internalRowSet.setRowId(parameterIndex, x);
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        internalRowSet.setRowId(parameterName, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        internalRowSet.setNString(parameterIndex, value);
    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        internalRowSet.setNString(parameterName, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        internalRowSet.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        internalRowSet.setNCharacterStream(parameterName, value, length);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        internalRowSet.setNCharacterStream(parameterName, value);
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        internalRowSet.setNClob(parameterName, value);
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        internalRowSet.setNClob(parameterName, reader, length);
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        internalRowSet.setNClob(parameterName, reader);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        internalRowSet.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        internalRowSet.setNClob(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        internalRowSet.setNClob(parameterIndex, reader);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        internalRowSet.setURL(parameterIndex, x);
    }

    @Override
    public boolean next() throws SQLException {
        return internalRowSet.next();
    }

    @Override
    public void close() throws SQLException {
        internalRowSet.close();
    }

    @Override
    public boolean wasNull() throws SQLException {
        return internalRowSet.wasNull();
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return internalRowSet.getString(columnIndex);
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        return internalRowSet.getBoolean(columnIndex);
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        return internalRowSet.getByte(columnIndex);
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        return internalRowSet.getShort(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        return internalRowSet.getInt(columnIndex);
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        return internalRowSet.getLong(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        return internalRowSet.getFloat(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        return internalRowSet.getDouble(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        return internalRowSet.getBigDecimal(columnIndex, scale);
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        return internalRowSet.getBytes(columnIndex);
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        return internalRowSet.getDate(columnIndex);
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        return internalRowSet.getTime(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return internalRowSet.getTimestamp(columnIndex);
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return internalRowSet.getAsciiStream(columnIndex);
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return internalRowSet.getUnicodeStream(columnIndex);
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return internalRowSet.getBinaryStream(columnIndex);
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return internalRowSet.getString(columnLabel);
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        return internalRowSet.getBoolean(columnLabel);
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        return internalRowSet.getByte(columnLabel);
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        return internalRowSet.getShort(columnLabel);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        return internalRowSet.getInt(columnLabel);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        return internalRowSet.getLong(columnLabel);
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        return internalRowSet.getFloat(columnLabel);
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        return internalRowSet.getDouble(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        return internalRowSet.getBigDecimal(columnLabel, scale);
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        return internalRowSet.getBytes(columnLabel);
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        return internalRowSet.getDate(columnLabel);
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        return internalRowSet.getTime(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        return internalRowSet.getTimestamp(columnLabel);
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        return internalRowSet.getAsciiStream(columnLabel);
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        return internalRowSet.getUnicodeStream(columnLabel);
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        return internalRowSet.getBinaryStream(columnLabel);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return internalRowSet.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        internalRowSet.clearWarnings();
    }

    @Override
    public String getCursorName() throws SQLException {
        return internalRowSet.getCursorName();
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return internalRowSet.getMetaData();
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        return internalRowSet.getObject(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        return internalRowSet.getObject(columnLabel);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        return internalRowSet.findColumn(columnLabel);
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        return internalRowSet.getCharacterStream(columnIndex);
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        return internalRowSet.getCharacterStream(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return internalRowSet.getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        return internalRowSet.getBigDecimal(columnLabel);
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return internalRowSet.isBeforeFirst();
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return internalRowSet.isAfterLast();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return internalRowSet.isFirst();
    }

    @Override
    public boolean isLast() throws SQLException {
        return internalRowSet.isLast();
    }

    @Override
    public void beforeFirst() throws SQLException {
        internalRowSet.beforeFirst();
    }

    @Override
    public void afterLast() throws SQLException {
        internalRowSet.afterLast();
    }

    @Override
    public boolean first() throws SQLException {
        return internalRowSet.first();
    }

    @Override
    public boolean last() throws SQLException {
        return internalRowSet.last();
    }

    @Override
    public int getRow() throws SQLException {
        return internalRowSet.getRow();
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        return internalRowSet.absolute(row);
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        return internalRowSet.relative(rows);
    }

    @Override
    public boolean previous() throws SQLException {
        return internalRowSet.previous();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        internalRowSet.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return internalRowSet.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        internalRowSet.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return internalRowSet.getFetchSize();
    }

    @Override
    public int getType() throws SQLException {
        return internalRowSet.getType();
    }

    @Override
    public int getConcurrency() throws SQLException {
        return internalRowSet.getConcurrency();
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        return internalRowSet.rowUpdated();
    }

    @Override
    public boolean rowInserted() throws SQLException {
        return internalRowSet.rowInserted();
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        return internalRowSet.rowDeleted();
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        internalRowSet.updateNull(columnIndex);
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        internalRowSet.updateBoolean(columnIndex, x);
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        internalRowSet.updateByte(columnIndex, x);
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        internalRowSet.updateShort(columnIndex, x);
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        internalRowSet.updateInt(columnIndex, x);
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        internalRowSet.updateLong(columnIndex, x);
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        internalRowSet.updateFloat(columnIndex, x);
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        internalRowSet.updateDouble(columnIndex, x);
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        internalRowSet.updateBigDecimal(columnIndex, x);
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        internalRowSet.updateString(columnIndex, x);
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        internalRowSet.updateBytes(columnIndex, x);
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        internalRowSet.updateDate(columnIndex, x);
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        internalRowSet.updateTime(columnIndex, x);
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        internalRowSet.updateTimestamp(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        internalRowSet.updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        internalRowSet.updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        internalRowSet.updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        internalRowSet.updateObject(columnIndex, x, scaleOrLength);
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        internalRowSet.updateObject(columnIndex, x);
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        internalRowSet.updateNull(columnLabel);
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        internalRowSet.updateBoolean(columnLabel, x);
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        internalRowSet.updateByte(columnLabel, x);
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        internalRowSet.updateShort(columnLabel, x);
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        internalRowSet.updateInt(columnLabel, x);
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        internalRowSet.updateLong(columnLabel, x);
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        internalRowSet.updateFloat(columnLabel, x);
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        internalRowSet.updateDouble(columnLabel, x);
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        internalRowSet.updateBigDecimal(columnLabel, x);
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        internalRowSet.updateString(columnLabel, x);
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        internalRowSet.updateBytes(columnLabel, x);
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        internalRowSet.updateDate(columnLabel, x);
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        internalRowSet.updateTime(columnLabel, x);
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        internalRowSet.updateTimestamp(columnLabel, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        internalRowSet.updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        internalRowSet.updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        internalRowSet.updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        internalRowSet.updateObject(columnLabel, x, scaleOrLength);
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        internalRowSet.updateObject(columnLabel, x);
    }

    @Override
    public void insertRow() throws SQLException {
        internalRowSet.insertRow();
    }

    @Override
    public void updateRow() throws SQLException {
        internalRowSet.updateRow();
    }

    @Override
    public void deleteRow() throws SQLException {
        internalRowSet.deleteRow();
    }

    @Override
    public void refreshRow() throws SQLException {
        internalRowSet.refreshRow();
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        internalRowSet.cancelRowUpdates();
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        internalRowSet.moveToInsertRow();
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        internalRowSet.moveToCurrentRow();
    }

    @Override
    public Statement getStatement() throws SQLException {
        return internalRowSet.getStatement();
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        return internalRowSet.getObject(columnIndex, map);
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        return internalRowSet.getRef(columnIndex);
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        return internalRowSet.getBlob(columnIndex);
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        return internalRowSet.getClob(columnIndex);
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        return internalRowSet.getArray(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return internalRowSet.getObject(columnLabel, map);
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        return internalRowSet.getRef(columnLabel);
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        return internalRowSet.getBlob(columnLabel);
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        return internalRowSet.getClob(columnLabel);
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        return internalRowSet.getArray(columnLabel);
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return internalRowSet.getDate(columnIndex, cal);
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return internalRowSet.getDate(columnLabel, cal);
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return internalRowSet.getTime(columnIndex, cal);
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        return internalRowSet.getTime(columnLabel, cal);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return internalRowSet.getTimestamp(columnIndex, cal);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        return internalRowSet.getTimestamp(columnLabel, cal);
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        return internalRowSet.getURL(columnIndex);
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        return internalRowSet.getURL(columnLabel);
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        internalRowSet.updateRef(columnIndex, x);
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        internalRowSet.updateRef(columnLabel, x);
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        internalRowSet.updateBlob(columnIndex, x);
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        internalRowSet.updateBlob(columnLabel, x);
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        internalRowSet.updateClob(columnIndex, x);
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        internalRowSet.updateClob(columnLabel, x);
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        internalRowSet.updateArray(columnIndex, x);
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        internalRowSet.updateArray(columnLabel, x);
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        return internalRowSet.getRowId(columnIndex);
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        return internalRowSet.getRowId(columnLabel);
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        internalRowSet.updateRowId(columnIndex, x);
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        internalRowSet.updateRowId(columnLabel, x);
    }

    @Override
    public int getHoldability() throws SQLException {
        return internalRowSet.getHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return internalRowSet.isClosed();
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        internalRowSet.updateNString(columnIndex, nString);
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        internalRowSet.updateNString(columnLabel, nString);
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        internalRowSet.updateNClob(columnIndex, nClob);
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        internalRowSet.updateNClob(columnLabel, nClob);
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        return internalRowSet.getNClob(columnIndex);
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        return internalRowSet.getNClob(columnLabel);
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        return internalRowSet.getSQLXML(columnIndex);
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        return internalRowSet.getSQLXML(columnLabel);
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        internalRowSet.updateSQLXML(columnIndex, xmlObject);
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        internalRowSet.updateSQLXML(columnLabel, xmlObject);
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        return internalRowSet.getNString(columnIndex);
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        return internalRowSet.getNString(columnLabel);
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        return internalRowSet.getNCharacterStream(columnIndex);
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        return internalRowSet.getNCharacterStream(columnLabel);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        internalRowSet.updateNCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        internalRowSet.updateNCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        internalRowSet.updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        internalRowSet.updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        internalRowSet.updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        internalRowSet.updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        internalRowSet.updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        internalRowSet.updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        internalRowSet.updateBlob(columnIndex, inputStream, length);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        internalRowSet.updateBlob(columnLabel, inputStream, length);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        internalRowSet.updateClob(columnIndex, reader, length);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        internalRowSet.updateClob(columnLabel, reader, length);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        internalRowSet.updateNClob(columnIndex, reader, length);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        internalRowSet.updateNClob(columnLabel, reader, length);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        internalRowSet.updateNCharacterStream(columnIndex, x);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        internalRowSet.updateNCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        internalRowSet.updateAsciiStream(columnIndex, x);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        internalRowSet.updateBinaryStream(columnIndex, x);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        internalRowSet.updateCharacterStream(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        internalRowSet.updateAsciiStream(columnLabel, x);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        internalRowSet.updateBinaryStream(columnLabel, x);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        internalRowSet.updateCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        internalRowSet.updateBlob(columnIndex, inputStream);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        internalRowSet.updateBlob(columnLabel, inputStream);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        internalRowSet.updateClob(columnIndex, reader);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        internalRowSet.updateClob(columnLabel, reader);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        internalRowSet.updateNClob(columnIndex, reader);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        internalRowSet.updateNClob(columnLabel, reader);
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return internalRowSet.getObject(columnIndex, type);
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return internalRowSet.getObject(columnLabel, type);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return internalRowSet.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return internalRowSet.isWrapperFor(iface);
    }

    @Override
    public void setMatchColumn(int columnIdx) throws SQLException {
        internalRowSet.setMatchColumn(columnIdx);
    }

    @Override
    public void setMatchColumn(int[] columnIdxes) throws SQLException {
        internalRowSet.setMatchColumn(columnIdxes);
    }

    @Override
    public void setMatchColumn(String columnName) throws SQLException {
        internalRowSet.setMatchColumn(columnName);
    }

    @Override
    public void setMatchColumn(String[] columnNames) throws SQLException {
        internalRowSet.setMatchColumn(columnNames);
    }

    @Override
    public int[] getMatchColumnIndexes() throws SQLException {
        return internalRowSet.getMatchColumnIndexes();
    }

    @Override
    public String[] getMatchColumnNames() throws SQLException {
        return internalRowSet.getMatchColumnNames();
    }

    @Override
    public void unsetMatchColumn(int columnIdx) throws SQLException {
        internalRowSet.unsetMatchColumn(columnIdx);
    }

    @Override
    public void unsetMatchColumn(int[] columnIdxes) throws SQLException {
        internalRowSet.unsetMatchColumn(columnIdxes);
    }

    @Override
    public void unsetMatchColumn(String columnName) throws SQLException {
        internalRowSet.unsetMatchColumn(columnName);
    }

    @Override
    public void unsetMatchColumn(String[] columnName) throws SQLException {
        internalRowSet.unsetMatchColumn(columnName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CachedRowSetWrapper that = (CachedRowSetWrapper) o;

        return internalRowSet.equals(that.internalRowSet);
    }

    @Override
    public int hashCode() {
        return internalRowSet.hashCode();
    }

    @Override
    public String toString() {
        return "CachedRowSetWrapper{" +
                "internalRowSet=" + internalRowSet +
                '}';
    }
}