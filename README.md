# FoodPalette update본 입니다.
FoodPalette update내용

1. Spring ver 3.2.3 -> 4.3.9 release로 변경 및 환경설정 파일들의 버젼업

2. oracleDB에서 mariaDB로 변경
(이것은 oracleDB와 mysql/mariaDB중 골라서 사용할 수 있도록 만들기위해 작업함)
추후에 Mapper를 oracleDB전용, mysql/mariaDB전용으로 나눠서 만들예정

톰캣7, jre 1.8.0_162 환경에서 테스트했습니다.
추후에 톰캣9버젼에서도 테스트해볼예정입니다.

혹시몰라서 C:\Users\사용자명\.m2\repository 경로에 생성되는 maven dependency들의 jar파일들이 정상적으로 설치되지 않을 경우를 대비하여 repository 디렉터리 내의 파일 및 폴더들을 통째로 업로드하겠습니다.
만약 pc에서 pom.xml에 적어놓은 dependency들의 jar파일을 정상적으로 설치하지 못할경우 첨부한 repository 폴더를 C:\Users\사용자명\.m2\repository 경로에 복사 및 붙여넣기 해주시면 됩니다.