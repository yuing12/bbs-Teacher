package mmms.member.svc;

import java.sql.Connection;

import mmms.member.dao.MemberDAO;
import mmms.member.db.JdbcUtil;

public class MemberRemoveService {
	
    public boolean removeMember(String name) {
    	boolean isRemoveSuccess = false;
    	
		Connection con = JdbcUtil.getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		
		int deleCount = memberDAO.deleteMember(name);
		if (deleCount > 0) {
			JdbcUtil.commit(con);// 1이상이면 커밋해라
			isRemoveSuccess = true;
		} else {
			JdbcUtil.rollback(con);// 아니면 작업 전으로 다시 되돌려라
		}
		JdbcUtil.close(con);

		return isRemoveSuccess;

	}
	

}
