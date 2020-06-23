SELECT SUM(a.count)
FROM (
         SELECT COUNT(*) AS count
         FROM email_blocklist
         WHERE email = ?
         UNION ALL
         SELECT COUNT(*) AS count
         FROM email_domain_blocklist
         WHERE domain = ?
     ) a