package cn.rock.transactiondemo.transaction;

import org.springframework.lang.Nullable;
import org.springframework.transaction.*;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author wjl48511
 * @create 2019/7/1-17:50
 **/
public class JdbcTransactionManager implements PlatformTransactionManager {

    private DataSource dataSource;

    public JdbcTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            TransactionResourceManager.bindResource(connection);
            return new DefaultTransactionStatus(connection, true, true, false, true, null);
        } catch (SQLException e) {
            throw new CannotCreateTransactionException("can't get connection for tx",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        Connection connection = (Connection) TransactionResourceManager.unbindResource();
        try {
//            connection.setAutoCommit(false);
//            connection.commit();
//            connection.rollback();
            connection.commit();
        }catch (SQLException e){
            throw new TransactionSystemException("commit error",e);
        }
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        Connection connection = (Connection) TransactionResourceManager.unbindResource();
        try {
            connection.rollback();
        }catch (SQLException e){
            throw new UnexpectedRollbackException("rollback error",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
