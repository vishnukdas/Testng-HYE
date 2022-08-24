1. Download and unzip the folder
2. Download concierge binary for your operating system from the below links and put it into above unzipped Parent directory:
    Windows: https://downloads.lambdatest.com/concierge/windows/concierge.exe
    Mac: https://downloads.lambdatest.com/concierge/darwin/concierge
    Linux: https://downloads.lambdatest.com/concierge/linux/concierge
3. Create .hypertest.yaml file under Parent directory
4. Once you have created '.hypertest.yaml', update it using one of the below steps based on mode/strategy you are choosing [Matrix/Autosplit]
5. For running tests using Matrix strategy, copy file content of 'hypertest_matrix_sample.yaml' to '.hypertest.yaml'
6. For running tests using Autosplit mode, copy file content of 'hypertest_autosplit_sample.yaml' to '.hypertest.yaml'
7. Replace username and accesskey with your LambdaTest credentials under '.hypertest.yaml' in testRunnerCommand or testSuites
8. Open Command prompt or terminal in the unzipped directory
9. For MAC/linux :  Give executable permission to concierge binary  'chmod u+x concierge'
9. Replace <username> and <key> with your LambdaTest credentials in the below commands, and run them in terminal
    for MAC/Linux: ./concierge --user <username> --key <key> --verbose --download-artifacts  --force-clean-artifacts
    for Windows: concierge.exe --user <username> --key <key> --verbose --download-artifacts  --force-clean-artifacts
10. Visit LambdaTest dashboard and see your jobs - https://automation.lambdatest.com/hypertest
11. To change platform, update platform parameter under 'src/test/java/testng.xml' file and runson[for autosplit] and os[for matrix] under '.hypertest.yaml'
   Note: values for:-
        runson and os = mac/win,
        platform = MacOS Catalina/Windows 10
12. To change browser, update browser parameter under 'src/test/java/testng.xml'
13. To change browser version, update version parameter under 'src/test/java/testng.xml'
