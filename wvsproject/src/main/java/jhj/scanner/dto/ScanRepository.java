package jhj.scanner.dto;

import org.springframework.data.repository.PagingAndSortingRepository;

//�ƹ��͵� �������� ���� ���� -CRUD�� ������ �⺻ �޼ҵ带 ���� �ִ� repository�� �ۼ��Ѵ�. 
//				spring-data���ο��� �ڵ����� ����
public interface ScanRepository extends PagingAndSortingRepository<scanDTO, String > {

}
