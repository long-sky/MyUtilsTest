github:

test add:
git add readme.txt
git commit -m "xxx"
git push origin master

test remove:
echo '.metadata' >> .gitignore
echo '.recommenders' >> .gitignore
git rm --cached -r .metadata
git rm --cached -r .recommenders
git add .gitignore
git commit -m "xxx"
git push origin master

git push origin master <!--�ѱ���Դ���push��Github��-->
git pull origin master <!--��Github��pull������Դ���-->
git config --list <!--�鿴������Ϣ-->
git status <!--�鿴��Ŀ״̬��Ϣ-->
git branch <!--�鿴��Ŀ��֧-->
git checkout -b host<!--���һ����Ϊhost�ķ�֧-->
git checkout master <!--�л�������-->
git merge host <!--�ϲ���֧host������-->
git branch -d host <!--ɾ����֧host-->