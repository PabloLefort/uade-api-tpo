FROM microsoft/mssql-server-linux

ENV ACCEPT_EULA Y 
ENV SA_PASSWORD Some_Password123
COPY setup.sql .
COPY entrypoint.sh .
EXPOSE 1433

CMD [ "/opt/mssql/bin/sqlservr" ]
CMD /bin/bash ./entrypoint.sh
