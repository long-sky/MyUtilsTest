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

git push origin master <!--把本地源码库push到Github上-->
git pull origin master <!--从Github上pull到本地源码库-->
git config --list <!--查看配置信息-->
git status <!--查看项目状态信息-->
git branch <!--查看项目分支-->
git checkout -b host<!--添加一个名为host的分支-->
git checkout master <!--切换到主干-->
git merge host <!--合并分支host到主干-->
git branch -d host <!--删除分支host-->